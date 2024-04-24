import React, { useEffect, useState } from "react";
import axios from "axios";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import UpdateUser from "./UpdateUser";
import DeleteUser from "./DeleteUser";
import "./User.css";

export default function BasicTable() {
    const [users, setUsers] = useState([]);
    const [editData, setEditData] = useState(null);
    const [deleteConfirm, setDeleteConfirm] = useState(null);
    const [accessToken, setAccessToken] = useState("");

    useEffect(() => {
        // Retrieve token from local storage
        const token = localStorage.getItem("token");
        if (token) {
            setAccessToken(token);
            // Fetch users from the backend API
            axios.get("/api/users", {
                headers: {
                    Authorization: `Bearer ${token}` // Include JWT token in the request headers
                }
            })
                .then(response => {
                    setUsers(response.data);
                })
                .catch(error => {
                    console.error("Error fetching users:", error);
                });
        }
    }, []);
    const handleDelete = (user) => {
        setDeleteConfirm(user);
    };

    const handleEdit = (user) => {
        setEditData(user);
    };

    const confirmDelete = () => {
        //  DELETE request
        axios.delete(`/api/users/${deleteConfirm.id}`)
            .then(() => {
                console.log("User deleted successfully");
                // fetch users again to update the table
                // Then close the confirmation popup
                setDeleteConfirm(null);
                // Fetch users again
                fetchUsers();
            })
            .catch(error => {
                console.error("Error deleting user:", error);
            });
    };

    const handleUpdate = (updatedUserData) => {
        //  PUT request
        axios.put(`/api/users/${updatedUserData.id}`, updatedUserData)
            .then(() => {
                console.log("User updated successfully");
                // After successful update, fetch users again to update the table
                // Then close the update popup
                setEditData(null);
                // Fetch users again
                fetchUsers();
            })
            .catch(error => {
                console.error("Error updating user:", error);
            });
    };
    const fetchUsers = () => {
        axios.get("/api/users")
            .then(response => {
                setUsers(response.data);
            })
            .catch(error => {
                console.error("Error fetching users:", error);
            });
    };

    const handleClose = () => {
        setEditData(null);
        setDeleteConfirm(null);
    };

    return (
        <div className="Table">
            <h3>Users</h3>
            <div className="TableContainerWrapper">
            <TableContainer
                component={Paper}
                style={{ boxShadow: "0px 13px 20px 0px #80808029" }}
            >
                <Table sx={{ minWidth: 650 }} aria-label="simple table">
                    <TableHead>
                        <TableRow>
                            <TableCell align="left">User ID</TableCell>
                            <TableCell align="left">Username</TableCell>
                            <TableCell align="left">Email</TableCell>
                            <TableCell align="left">Password</TableCell>
                            <TableCell align="left">Phone Number</TableCell>
                            <TableCell align="left">Delete</TableCell>
                            <TableCell align="left">Update</TableCell>
                        </TableRow>
                    </TableHead>
                    <TableBody style={{ color: "white" }}>
                        {users.map((user) => (
                            <TableRow
                                key={user.id}
                                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">
                                    {user.id}
                                </TableCell>
                                <TableCell component="th" scope="row">
                                    {user.username}
                                </TableCell>
                                <TableCell align="left">{user.email}</TableCell>
                                <TableCell align="left"  className="password-cell">{user.password}</TableCell>
                                <TableCell align="left"  className="phone-cell">{user.phoneNumber}</TableCell>
                                <TableCell align="left">
                                    <DeleteIcon onClick={() => handleDelete(user)} />
                                </TableCell>
                                <TableCell align="left">
                                    <EditIcon onClick={() => handleEdit(user)} />
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            {editData && (
                <UpdateUser userData={editData} handleUpdate={handleUpdate} handleClose={handleClose} />
            )}
            {deleteConfirm && (
                <DeleteUser handleDelete={confirmDelete} handleClose={handleClose} />
            )}
        </div>
        </div>
    );
}
