import '@testing-library/jest-dom';
import React from 'react';
import { render, screen, fireEvent, waitFor } from '@testing-library/react';
//import axios from 'axios';
import UserItemsList from '../components/UserItemsList';
const axios = require('axios/dist/node/axios.cjs');
// 1) Mock the entire axios module
jest.mock('axios');
const mockedAxios = axios;    // no TS cast here

const mockItems = [
  { id: 1, name: 'Apple' },
  { id: 2, name: 'Banana' },
  { id: 3, name: 'Grapes' }
];

describe('UserItemsList', () => {
  beforeEach(() => {
    jest.clearAllMocks();
  });

  test('renders all items initially', async () => {
    // stub the initial GET
    mockedAxios.get.mockResolvedValueOnce({ data: mockItems });

    render(<UserItemsList />);

    await waitFor(() => {
      expect(screen.getByText('Apple')).toBeInTheDocument();
      expect(screen.getByText('Banana')).toBeInTheDocument();
    });
  });

  test('filters items when typing in search', async () => {
    // 1st call: load all
    mockedAxios.get.mockResolvedValueOnce({ data: mockItems });
    // 2nd call: after typing
    mockedAxios.get.mockResolvedValueOnce({ data: [mockItems[0]] });

    render(<UserItemsList />);

    await waitFor(() => screen.getByText('Apple'));

    const input = screen.getByPlaceholderText(/search/i);
    fireEvent.change(input, { target: { value: 'Ap' } });

    await waitFor(() => {
      expect(screen.getByText('Apple')).toBeInTheDocument();
    });
  });

  test('shows "No results found" when API returns empty list', async () => {
    mockedAxios.get.mockResolvedValueOnce({ data: [] });

    render(<UserItemsList />);

    await waitFor(() => {
      expect(screen.getByText(/no results found/i)).toBeInTheDocument();
    });
  });
});
