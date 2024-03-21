import React from "react";
import "./RecentActivities.css";
import { RecentData } from "../../Data/Data";
import EastIcon from '@mui/icons-material/East';

const RecentActivities = () => {
    return (
        <div className="RecentActivities">
            <h2 className="title">Recent Activities</h2>
            <div className="activities">
                {RecentData.map((recent, index) => {
                    return (
                        <div className="recent" key={index}>
                            <div className="userOrder">
                                <img src={recent.img} alt="profile"/>
                                <div className="orderText">
                                    <span className="name">{recent.name}</span>
                                    <br/>
                                    <span className="text">{recent.text}</span>
                                </div>
                            </div>
                            <div className="time">
                                <span>{recent.time}</span>
                            </div>
                        </div>
                    );
                })}
                <button className="seeAll">
                    <span>See All</span>
                    <EastIcon/>
                </button>
            </div>
        </div>
    );
};

export default RecentActivities;

