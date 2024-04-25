import React, { useState } from "react";

const CreateUser = ({ handleCreate, handleClose }) => {
    const [userData, setUserData] = useState({
        username: "",
        email: "",
        password: "",
        phoneNumber: "",
    });

    const handleChange = (e) => {
        const { name, value } = e.target;
        setUserData(prevUserData => ({
            ...prevUserData,
            [name]: value
        }));
    };

    const handleSubmit = () => {
        handleCreate(userData);
        handleClose();
    };

    return (
        <div className="popup">
            <div className="popup-inner">
                <h2>Create New User</h2>
                <input
                    type="text"
                    name="username"
                    placeholder="Username"
                    value={userData.username}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="email"
                    placeholder="Email"
                    value={userData.email}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="password"
                    placeholder="Password"
                    value={userData.password}
                    onChange={handleChange}
                />
                <input
                    type="text"
                    name="phoneNumber"
                    placeholder="Phone Number"
                    value={userData.phoneNumber}
                    onChange={handleChange}
                />
                <div className="popup-buttons">
                    <button onClick={handleSubmit}>Create</button>
                    <button onClick={handleClose}>Cancel</button>
                </div>
            </div>
        </div>
    );
};

export default CreateUser;
