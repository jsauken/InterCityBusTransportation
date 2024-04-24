import React, { useState } from "react";

const UpdateUser = ({ userData, handleUpdate, handleClose }) => {
    const [newUserData, setNewUserData] = useState({
        username: userData.username || "",
        email: userData.email || "",
        password: userData.password || "",
        phoneNumber: userData.phoneNumber || "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setNewUserData({ ...newUserData, [name]: value });
    };

    const handleSubmit = () => {
        handleUpdate(newUserData);
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
                    <button onClick={handleSubmit}>Update</button>
                    <button onClick={handleClose}>Cancel</button>
                </div>
            </div>
        </div>
    );
};

export default UpdateUser;
