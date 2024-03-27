import React, { useState } from "react";
import { makeStyles } from "@mui/styles";
import { CalendarToday } from "@mui/icons-material";
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DatePicker } from '@mui/x-date-pickers/DatePicker';
import { TextField, InputAdornment } from "@mui/material";
import dayjs from 'dayjs';
import "./Calendar.css";

const useStyles = makeStyles((theme) => ({
    calendar: {
        background: "linear-gradient(to bottom, coral, pink)",
        borderRadius: "10px",
        padding: "20px",
        boxShadow: "0 4px 8px rgba(0, 0, 0, 0.1)",
        position: "relative",
        color:"#242d49"
    },
    title: {
        color: "white",
        textAlign: "center",
    },
}));

const Calendar = () => {
    const classes = useStyles();
    const [selectedDate, setSelectedDate] = useState(null);

    const routesData = [
        { from: "Almaty", to: "Astana", date: "2024-03-12" },
        { from: "Almaty", to: "Shymkent", date: "2024-03-12" },
        { from: "Astana", to: "Aktobe", date: "2024-03-13" },
    ];

    const handleDateChange = (date) => {
        setSelectedDate(date ? date.format("YYYY-MM-DD") : null); // Format selected date as string
    };

    const filteredRoutes = routesData.filter(route => route.date === selectedDate);

    return (
        <div className={classes.calendar}>
            <h2 className={classes.title}>Calendar</h2>

            <LocalizationProvider dateAdapter={AdapterDayjs}>
                <DatePicker
                    label="Select Date"
                    value={selectedDate ? dayjs(selectedDate) : null} // Convert selectedDate to Day.js object
                    onChange={handleDateChange}
                    renderInput={(params) => (
                        <TextField
                            {...params}
                            InputProps={{
                                startAdornment: (
                                    <InputAdornment position="start">
                                        <CalendarToday />
                                    </InputAdornment>
                                ),
                            }}
                        />
                    )}
                />
            </LocalizationProvider>

            {selectedDate && (
                <div className="routes">
                    <h3>Routes for {selectedDate}</h3>
                    <ul>
                        {filteredRoutes.map((route, index) => (
                            <li key={index}>
                                From: {route.from}
                                <br/>
                                To: {route.to}
                            </li>
                        ))}
                    </ul>
                </div>
            )}
        </div>
    );
};

export default Calendar;
