import './App.css';
import { Route, Routes, useLocation } from 'react-router-dom';
import RightSide from './components/RightSide/RightSide';
import MainDash from './components/MainDash/MainDash';
import Sidebar from './components/Sidebar/Sidebar';
import RouteTable from './components/Route/Route';
import Order from './components/Order/Order';
import Users from './components/User/User';
import Analytics from './components/Analytics/Analytics';
import Login from './components/Auth/Login';
import Register from './components/Auth/Register';

function App() {
    const location = useLocation();
    const hideSidebarAndRightSide = location.pathname === '/login' || location.pathname === '/register';

    return (
        <div className="App">
            <div className="AppGlass">
                {!hideSidebarAndRightSide && <Sidebar/>}
                <Routes>
                    <Route path="/" element={<MainDash/>} />
                    <Route path="/dashboard" element={<MainDash/>} />
                    <Route path="/right-side" element={<RightSide/>} />
                    <Route path="/table" element={<Order/>} />
                    <Route path="/routes" element={<RouteTable/>} />
                    <Route path="/users" element={<Users/>} />
                    <Route path="/analytics" element={<Analytics/>} />
                    <Route path="/login" element={<Login/>} />
                    <Route path="/register" element={<Register/>} />
                </Routes>
                {!hideSidebarAndRightSide && <RightSide/>}
            </div>
        </div>
    );
}

export default App;
