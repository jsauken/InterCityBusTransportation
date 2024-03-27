// DeleteUser Popup
import React from "react";

const DeleteUser = ({ handleDelete, handleClose }) => {
    return (
        <div className="popup">
            <div className="popup-inner">
                <h2>Confirm Delete</h2>
                <p>Are you sure you want to delete this item?</p>
                <div className="popup-buttons">
                    <button onClick={handleDelete}>Yes</button>
                    <button onClick={handleClose}>No</button>
                </div>
            </div>
        </div>
    );
};

export default DeleteUser;
