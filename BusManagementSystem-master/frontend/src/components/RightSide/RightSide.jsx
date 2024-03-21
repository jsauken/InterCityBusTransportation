import React from "react";
import RecentActivities from "../RecentActivities/RecentActivities";
import "./RightSide.css";
import Calendar from "../Calendar/Calendar";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

const RightSide = () => {
    return (
        <div className="RightSide">
            <div>
                <RecentActivities />
            </div>
            <div>
                <LocalizationProvider dateAdapter={AdapterDayjs}>
                    <Calendar />
                </LocalizationProvider>
            </div>
        </div>
    );
};

export default RightSide;
