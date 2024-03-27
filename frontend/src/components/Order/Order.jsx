import React from 'react';
import Table from '@mui/material/Table';
import TableBody from '@mui/material/TableBody';
import TableCell from '@mui/material/TableCell';
import TableContainer from '@mui/material/TableContainer';
import TableHead from '@mui/material/TableHead';
import TableRow from '@mui/material/TableRow';
import Paper from '@mui/material/Paper';
import "./Order.css";


const orders = [
    { id: 1, departureTime: '10:00 AM', route: 'Almaty - Astana', bus: 'Bus 1', price: '2500 ₸', arrivalTime: '1:00 PM' },
    { id: 2, departureTime: '11:00 AM', route: 'Pavlodar - Astana', bus: 'Bus 2', price: '1400 ₸', arrivalTime: '2:00 PM' },
    { id: 3, departureTime: '12:00 PM', route: 'Almaty - Taldykorgan', bus: 'Bus 3', price: '750 ₸', arrivalTime: '3:00 PM' },
];

const Order = () => {
    return (
        <TableContainer component={Paper}>
            <Table>
                <TableHead>
                    <TableRow>
                        <TableCell>Departure Time</TableCell>
                        <TableCell>Route</TableCell>
                        <TableCell>Bus</TableCell>
                        <TableCell>Price</TableCell>
                        <TableCell>Arrival Time</TableCell>
                        <TableCell>Actions</TableCell> {/* Added Actions column */}
                    </TableRow>
                </TableHead>
                <TableBody>
                    {orders.map(order => (
                        <TableRow key={order.id}>
                            <TableCell>{order.departureTime}</TableCell>
                            <TableCell>{order.route}</TableCell>
                            <TableCell>{order.bus}</TableCell>
                            <TableCell>{order.price}</TableCell>
                            <TableCell>{order.arrivalTime}</TableCell>
                            <TableCell>
                                <button>Edit</button>
                                <button>Delete</button>
                            </TableCell>
                        </TableRow>
                    ))}
                </TableBody>
            </Table>
        </TableContainer>
    );
};

export default Order;
