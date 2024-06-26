
import React, { useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axios from "axios";
import "./Auth.css";

function Register() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [phoneNumber, setPhoneNumber] = useState("");
    const [error, setError] = useState("");
    const navigate = useNavigate(); // Use useNavigate for navigation

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("/api/auth/signup", { name, email, password, phoneNumber });
            // Redirect to login page upon successful registration
            navigate("/login"); // Use navigate instead of history.push
        } catch (error) {
            setError("Email is already registered");
        }
    };

    return (
        <div className="AuthContainer">
            <div className="AuthForm">
            <h2>Register</h2>
            {error && <div className="error">{error}</div>}
                <form onSubmit={handleSubmit}>
                    <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)}
                           required/>
                    <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)}
                           required/>
                    <input type="password" placeholder="Password" value={password}
                           onChange={(e) => setPassword(e.target.value)} required/>
                    <input type="number" placeholder="Phone number" value={phoneNumber}
                           onChange={(e) => setPhoneNumber(e.target.value)} />
                    <button type="submit">Register</button>
                </form>
                <Link to="/login">Already have an account? Login here</Link>
            </div>
        </div>
    );
}

export default Register;
