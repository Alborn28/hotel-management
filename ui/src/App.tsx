import { BrowserRouter, Navigate, Route, Routes } from "react-router-dom";
import Home from "./views/HomeView";
import BookingView from "./views/BookingView";

const App = () => (
    <BrowserRouter>
        <Routes>
            <Route path="/" element={<Home />} />
            <Route path="/book" element={<BookingView />} />
            <Route path="*" element={<Navigate to="/" />} />
        </Routes>
    </BrowserRouter>
);

export default App;
