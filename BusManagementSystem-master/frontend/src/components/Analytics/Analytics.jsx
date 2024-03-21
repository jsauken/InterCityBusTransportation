import React from "react";
import { Line, Bar } from "react-chartjs-2";
import "./Analytics.css";
import Chart from 'chart.js/auto';

const Analytics = () => {
    const horizontalBarChartData = {
        labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July'],
        datasets: [
            {
                label: 'Number of New Users',
                data: [65, 59, 80, 81, 56, 55, 40],
                backgroundColor: 'rgba(54, 162, 235, 0.2)',
                borderColor: 'rgba(54, 162, 235, 1)',
                borderWidth: 1,
            },
        ],
        options: {
            aspectRatio: 2,
            scales: {
                x: {
                    type: 'linear',
                    display: true,
                    title: {
                        display: true,
                        text: 'Month'
                    }
                },
                y: {
                    type: 'category',
                    display: true,
                    title: {
                        display: true,
                        text: 'Number of New Users'
                    }
                }
            },
        },
    };

    const barChartData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "Revenue",
                backgroundColor: "#28a745",
                borderColor: "#28a745",
                borderWidth: 1,
                hoverBackgroundColor: "#28a745",
                hoverBorderColor: "#28a745",
                data: [203000, 1030000, 580000, 491000, 386000, 155000, 940000],
            },
        ],
        options: {
            aspectRatio: 2,
            scales: {
                x: {
                    type: 'category',
                    display: true,
                    title: {
                        display: true,
                        text: 'Month'
                    }
                },
                y: {
                    type: 'linear',
                    display: true,
                    title: {
                        display: true,
                        text: 'Revenue'
                    }
                }
            },
        },
    };
    const lineChartData = {
        labels: ["January", "February", "March", "April", "May", "June", "July"],
        datasets: [
            {
                label: "Orders",
                data: [65, 59, 80, 81, 56, 55, 40],
                fill: false,
                borderColor: "#007bff",
            },
        ],
        options: {
            aspectRatio: 2,
            scales: {
                x: {
                    type: 'category',
                    display: true,
                    title: {
                        display: true,
                        text: 'Month'
                    }
                },
                y: {
                    type: 'linear',
                    display: true,
                    title: {
                        display: true,
                        text: 'Orders'
                    }
                }
            },
        },
    };

    return (
        <div>
            <h2>Analytics</h2>
            <div className='charts'>
                <div className='chart'>
                    <h3>Sales Trend</h3>
                    <Line data={lineChartData} />
                </div>
                <div className='chart'>
                    <h3>Number of New Users</h3>
                    <Bar data={horizontalBarChartData} />
                </div>
                <div className='chart'>
                    <h3>Monthly Revenue</h3>
                    <Bar data={barChartData} />
                </div>
            </div>
        </div>
    );
};

export default Analytics;
