import './App.css'
import RightSide from './components/RightSide/RightSide';
import MainDash from './components/MainDash/MainDash';
import Sidebar from './components/Sidebar/Sidebar';
import RouteTable from './components/Route/Route';
import Order from './components/Order/Order';
import Users from './components/User/User';
import {Route, Routes } from 'react-router-dom';
import Analytics from './components/Analytics/Analytics';



function App() {
    return (
        <div className="App">
            <div className="AppGlass">
                <Sidebar/>
                <Routes>
                    <Route path="/" exact element={<MainDash/>} />
                    <Route path="/dashboard" element={<MainDash/>} />
                    <Route path="/right-side" element={<RightSide/>} />
                    <Route path="/table" element={<Order/>} />
                    <Route path="/routes" element={<RouteTable/>} />
                    <Route path="/users" element={<Users/>} />
                    <Route path="/analytics" element={<Analytics/>} />
                </Routes>
                <RightSide/>
            </div>
        </div>
    );
}

export default App;
