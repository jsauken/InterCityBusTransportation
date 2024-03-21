import * as React from "react";
import Table from "@mui/material/Table";
import TableBody from "@mui/material/TableBody";
import TableCell from "@mui/material/TableCell";
import TableContainer from "@mui/material/TableContainer";
import TableHead from "@mui/material/TableHead";
import TableRow from "@mui/material/TableRow";
import Paper from "@mui/material/Paper";
import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import "./User.css";

function createData(userID, username, password, email, phoneNumber) {
    return { userID, username, password, email, phoneNumber };
}


const rows = [
    createData("1000", "saukenj", "********", "saukenzh@gmail.com", "+7 775 091 5236"),
    createData("1001", "mergembaeva", "********", "aidanamergembaeva@gmail.com", "+7 747 420 4253"),
    createData("1002", "pazilkan20.03", "********", "pazilkhanaidana20.03@gmail.com", "+7 771 657 0399"),
    createData("1003", "sayawka", "********", "jansaya11jan@gmail.com", "+7 707 789 5504"),
    createData("1004", "Alisher_91", "********", "alisher.91@gmail.com", "+7 701 234 5678"),
    createData("1005", "NurlanAbay", "********", "nurlanabay@example.kz", "+7 702 345 6789"),
    createData("1006", "jamshut", "********", "murad.jamshut@example.kz", "+7 703 456 7890"),
    createData("1007", "svetLana", "********", "sveta.uzbekova@example.kz", "+7 704 567 8901"),
    createData("1008", "Rustem_Bekzhan", "********", "rustem.bekzhan@example.kz", "+7 705 678 9012"),
    createData("1009", "Janaya_Joldybek", "********", "janaya.joldybek@example.kz", "+7 706 789 0123"),
    createData("1010", "Aziz_Orlanov", "********", "aziz.orlanov@example.kz", "+7 707 890 1234"),
    createData("1011", "Zhanar_Sabyrzhan", "********", "zhanar.sabyrzhan@example.kz", "+7 708 901 2345"),
    createData("1012", "Igor_Yuryevich", "********", "igor.yuryevich@example.kz", "+7 709 012 3456"),
    createData("1013", "Aigul_Toleubek", "********", "aigul.toleubek@example.kz", "+7 701 234 5678")
];

const handleClosePopup = (setPopup) => {
    setPopup(null);
};

export default function BasicTable() {
    const [editData, setEditData] = React.useState(null);
    const [deleteConfirm, setDeleteConfirm] = React.useState(null);

    const handleEdit = (row) => {
        setEditData(row);
    };

    const handleDelete = (row) => {
        setDeleteConfirm(row);
    };

    const confirmDelete = () => {
        console.log("Deleting user with ID:", deleteConfirm.userID);
        setDeleteConfirm(null);
    };


    return (
        <div className="Table">
            <h3>Recent Orders</h3>
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
                        {rows.map((row) => (
                            <TableRow
                                key={row.userID}
                                sx={{ "&:last-child td, &:last-child th": { border: 0 } }}
                            >
                                <TableCell component="th" scope="row">
                                    {row.userID}
                                </TableCell>
                                <TableCell component="th" scope="row">
                                    {row.username}
                                </TableCell>
                                <TableCell align="left">{row.email}</TableCell>
                                <TableCell align="left">{row.password}</TableCell>
                                <TableCell align="left">{row.phoneNumber}</TableCell>
                                <TableCell align="left">
                                    <DeleteIcon onClick={() => handleDelete(row)} />
                                </TableCell>
                                <TableCell align="left">
                                    <EditIcon onClick={() => handleEdit(row)} />
                                </TableCell>
                            </TableRow>
                        ))}
                    </TableBody>
                </Table>
            </TableContainer>
            {editData && (
                // Popup for editing user data
                <div className="overlay" onClick={() => handleClosePopup(setEditData)}>
                    <div className="popup">
                        <h2>Edit User Data</h2>
                        <p>User ID: {editData.userID}</p>
                        <input
                            type="text"
                            placeholder="Enter new username"
                            value={editData.username}
                            onChange={(e) => setEditData({...editData, username: e.target.value})}
                        />
                        <input
                            type="text"
                            placeholder="Enter new email"
                            value={editData.email}
                            onChange={(e) => setEditData({...editData, email: e.target.value})}
                        />
                        <input
                            type="text"
                            placeholder="Enter new phone number"
                            value={editData.phoneNumber}
                            onChange={(e) => setEditData({...editData, phoneNumber: e.target.value})}
                        />
                        <button onClick={handleClosePopup}>Submit</button>
                        <button onClick={handleClosePopup}>Cancel</button>
                    </div>
                </div>
            )}
            {deleteConfirm && (
                // Confirmation popup for deleting a user
                <div className="overlay" onClick={() => handleClosePopup(setDeleteConfirm)} >
                    <div className="popup">
                        <h2>Confirm Deletion</h2>
                        <p>Are you sure you want to delete user {deleteConfirm.username}?</p>
                        <button onClick={confirmDelete}>Yes</button>
                        <button onClick={() => setDeleteConfirm(null)}>No</button>
                    </div>
                </div>
            )}
        </div>
    );
}
