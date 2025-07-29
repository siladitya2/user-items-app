jest.mock('axios');
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
import axios from 'axios';
import UserItemsList from '../components/UserItemsList';

jest.mock('axios');

const mockItems = [
    { id: 1, name: 'Apple' },
    { id: 2, name: 'Banana' },
    { id: 3, name: 'Grapes' }
];

describe('UserItemsList', () => {
    beforeEach(() => {
        axios.get.mockResolvedValue({ data: mockItems });
    });

    test('renders all items initially', async () => {
        render(<UserItemsList />);
        await waitFor(() => {
            expect(screen.getByText('Apple')).toBeInTheDocument();
            expect(screen.getByText('Banana')).toBeInTheDocument();
        });
    });

    test('filters items when typing in search', async () => {
        render(<UserItemsList />);
        const searchInput = screen.getByPlaceholderText(/search/i);

        fireEvent.change(searchInput, { target: { value: 'Ap' } });
        axios.get.mockResolvedValue({ data: [mockItems[0]] });

        await waitFor(() => {
            expect(screen.getByText('Apple')).toBeInTheDocument();
            expect(screen.queryByText('Banana')).not.toBeInTheDocument();
        });
    });

    test('shows "No results found" when API returns empty list', async () => {
        axios.get.mockResolvedValue({ data: [] });
        render(<UserItemsList />);
        await waitFor(() => {
            expect(screen.getByText(/no results found/i)).toBeInTheDocument();
        });
    });
});
