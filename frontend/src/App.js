import './App.css';
import api from './api/axiosConfig';
import {useState, useEffect} from 'react';
import Layout from "./components/Layout";
import {Routes, Route} from "react-router-dom";
import Home from "./components/home/Home";

function App() {
    const [tasks, setTasks] = useState();

    const getTasks = async () => {
        try {
            const response = await api.get('/api/tasks');
            console.log(response.data);
            setTasks(response.data);
        } catch (error) {
            console.log(error);
        }
    }
    useEffect(() => {
        getTasks();
    }, []);
    return (
        <div className="App">
            <Routes>
                <Route path="/" element={<Layout/>}>
                    <Route path="/" element={<Home tasks={tasks}/>}></Route>
                </Route>
            </Routes>

        </div>
    );
}

export default App;