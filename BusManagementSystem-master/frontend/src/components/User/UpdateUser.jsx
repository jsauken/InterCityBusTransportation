// Update Popup
import React, { useState } from "react";

const UpdateUser = ({ userData, handleUpdate, handleClose }) => {
    const [newUserData, setNewUserData] = useState(userData);

    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewUserData({ ...newUserData, [name]: value });
    };

    return (
        <div className="popup">
            <div className="popup-inner">
                <h2>Update User Data</h2>
                <input
                    type="text"
                    name="username"
                    value={newUserData.username}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="email"
                    value={newUserData.email}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="password"
                    value={newUserData.password}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="phoneNumber"
                    value={newUserData.phoneNumber}
                    onChange={handleChange}
                />
                <div className="popup-buttons">
                    <button onClick={() => handleUpdate(newUserData)}>Update</button>
                    <button onClick={handleClose}>Cancel</button>
                </div>
            </div>
        </div>
    );
};

export default UpdateUser;
