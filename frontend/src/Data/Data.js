
import {
    UilEstate,
    UilClipboardAlt,
    UilUsersAlt,
    UilPackage,
    UilChart,
} from "@iconscout/react-unicons";


import { UilUsdSquare, UilMoneyWithdrawal } from "@iconscout/react-unicons";



import img1 from "../imgs/img1.png";
import img2 from "../imgs/img2.png";
import img3 from "../imgs/img3.png";


export const SidebarData = [
    {
        icon: UilEstate,
        heading: "Dashboard",
        path:"/dashboard"
    },
    {
        icon: UilClipboardAlt,
        heading: "Orders",
        path:"/table"
    },
    {
        icon: UilUsersAlt,
        heading: "Users",
        path:"/users"
    },
    {
        icon: UilPackage,
        heading: 'Routes',
        path:"/routes"
    },
    {
        icon: UilChart,
        heading: 'Analytics',
        path:"/analytics"
    },
];


export const cardsData = [
    {
        title: "Sales",
        color: {
            backGround: "linear-gradient(180deg, #FFA07A 0%, #FF7F50 100%)", // Light orange gradient
            boxShadow: "0px 10px 20px 0px rgba(255, 140, 102, 0.3)", // Soft shadow
        },
        barValue: 70,
        value: "25,970",
        png: UilUsdSquare,
        series: [
            {
                name: "Sales",
                data: [31, 40, 28, 51, 42, 109, 100],
            },
        ],
    },
    {
        title: "Revenue",
        color: {
            backGround: "linear-gradient(180deg, #FFD700 0%, #FFA500 100%)", // Gold gradient
            boxShadow: "0px 10px 20px 0px rgba(255, 215, 0, 0.3)", // Soft shadow
        },
        barValue: 80,
        value: "14,270",
        png: UilMoneyWithdrawal,
        series: [
            {
                name: "Revenue",
                data: [10, 100, 50, 70, 80, 30, 40],
            },
        ],
    },
    {
        title: "Expenses",
        color: {
            backGround: "linear-gradient(180deg, #FF6347 0%, #FF4500 100%)", // Tomato gradient
            boxShadow: "0px 10px 20px 0px rgba(255, 99, 71, 0.3)", // Soft shadow
        },
        barValue: 60,
        value: "4,270",
        png: UilClipboardAlt,
        series: [
            {
                name: "Expenses",
                data: [10, 25, 15, 30, 12, 15, 20],
            },
        ],
    },
];


export const RecentData = [
    {
        img: img1,
        name: "Jasmin Sauken",
        text: "has ordered Ticket Almaty - Astana",
        time: "25 sec ago",
    },
    {
        img: img2,
        name: "Aidana Mergembaeva",
        text: "has declined Ticket Astana - Pavlodar",
        time: "30 min ago",
    },
    {
        img: img3,
        name: "Aidana Pazylkhan",
        text: "has ordered Ticket Almaty - Shymkent",
        time: "2 h ago",
    },
];
