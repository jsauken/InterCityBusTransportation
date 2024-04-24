import React from "react";

const DeleteUser = ({ handleDelete, handleClose }) => {
    // Initialize state values to empty strings to avoid warnings related to uncontrolled components
    const [confirmation, setConfirmation] = React.useState("");

    const handleChange = (e) => {
        const { value } = e.target;
        setConfirmation(value);
    };

    return (
        <div className="popup">
            <div className="popup-inner">
                <h2>Confirm Delete</h2>
                <p>Are you sure you want to delete this item?</p>
                <input
                    type="text"
                    value={confirmation}
                    onChange={handleChange}
                    style={{ display: "none" }}
                />
                <div className="popup-buttons">
                    <button onClick={handleDelete}>Yes</button>
                    <button onClick={handleClose}>No</button>
                </div>
            </div>
        </div>
    );
};

export default DeleteUser;
