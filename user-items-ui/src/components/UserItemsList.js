import React, { useState, useEffect } from 'react';
import axios from 'axios';

const API_BASE =
    process.env.REACT_APP_API_URL || 'http://localhost:8080/api/v1/items';

const UserItemsList = () => {
    const [items, setItems] = useState([]);
    const [search, setSearch] = useState('');
    const [error, setError] = useState('');
    const [loading, setLoading] = useState(false);

    const fetchItems = async (term = '') => {
        try {
            setLoading(true);
            setError('');
            const url = term ? `${API_BASE}?filter=${term}` : API_BASE;
            const response = await axios.get(url);
            setItems(response.data.content || response.data); // Handles paginated API
        } catch (err) {
            if (err.response) {
                // API responded with an error (4xx, 5xx)
                setError(
                    err.response.data.message ||
                    'Server returned an error. Please try again later.'
                );
            } else if (err.request) {
                // Request was sent but no response
                setError('Network error: Unable to reach the server.');
            } else {
                // Something else went wrong
                setError('An unexpected error occurred.');
            }
        } finally {
            setLoading(false);
        }
    };

    useEffect(() => {
        fetchItems();
    }, []);

    const handleSearch = (e) => {
        const value = e.target.value;
        setSearch(value);
        fetchItems(value);
    };

    return (
        <div style={{ padding: '1rem', maxWidth: '400px' }}>
            <input
                type="text"
                placeholder="Search..."
                value={search}
                onChange={handleSearch}
                style={{
                    width: '100%',
                    padding: '0.5rem',
                    marginBottom: '1rem',
                    border: '1px solid #ccc',
                    borderRadius: '4px',
                }}
            />

            {loading && <p>Loading items...</p>}
            {error && <p style={{ color: 'red' }}>{error}</p>}

            <ul style={{ listStyle: 'none', padding: 0 }}>
                {!loading && !error && items.length > 0 ? (
                    items.map((item) => (
                        <li key={item.id} style={{ padding: '0.25rem 0' }}>
                            {item.name}
                        </li>
                    ))
                ) : (
                    !loading &&
                    !error && <li style={{ color: '#888' }}>No results found</li>
                )}
            </ul>
        </div>
    );
};

export default UserItemsList;
