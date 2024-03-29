import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import axios from "axios";

function Register() {
    const [name, setName] = useState("");
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const history = useHistory();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            await axios.post("/api/auth/signup", { name, email, password });
            // Redirect to login page upon successful registration
            history.push("/login");
        } catch (error) {
            setError("Email is already registered");
        }
    };

    return (
        <div>
            <h2>Register</h2>
            {error && <div>{error}</div>}
            <form onSubmit={handleSubmit}>
                <input type="text" placeholder="Name" value={name} onChange={(e) => setName(e.target.value)} required />
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit">Register</button>
            </form>
            <Link to="/login">Already have an account? Login here</Link>
        </div>
    );
}

export default Register;
