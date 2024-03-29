import React, { useState } from "react";
import { Link, useHistory } from "react-router-dom";
import axios from "axios";

function Login() {
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [error, setError] = useState("");
    const history = useHistory();

    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post("/api/auth/login", { email, password });
            // Store token in local storage upon successful login
            localStorage.setItem("token", response.data.token);
            // Redirect to dashboard or desired page upon successful login
            history.push("/dashboard");
        } catch (error) {
            setError("Invalid email or password");
        }
    };

    return (
        <div>
            <h2>Login</h2>
            {error && <div>{error}</div>}
            <form onSubmit={handleSubmit}>
                <input type="email" placeholder="Email" value={email} onChange={(e) => setEmail(e.target.value)} required />
                <input type="password" placeholder="Password" value={password} onChange={(e) => setPassword(e.target.value)} required />
                <button type="submit">Login</button>
            </form>
            <Link to="/register">Don't have an account? Register here</Link>
        </div>
    );
}

export default Login;
