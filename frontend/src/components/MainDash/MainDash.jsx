import React from "react";
import Cards from "../Cards/Cards";
import "./MainDash.css";
import CustomerReview from "../CustomerReview/CustomerReview";

const MainDash = () => {
    return (
        <div className="MainDash">
            <h1>Dashboard</h1>
            <Cards />
            <div className="customer">
                <h1>Customer Review</h1>
                <div className="chart-container" >
                    <CustomerReview />
                </div>
            </div>
        </div>
    );
};

export default MainDash;

