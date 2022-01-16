import { BrowserRouter, Routes, Route } from "react-router-dom";
import Main from "./Routes/main/Main";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path={`${process.env.PUBLIC_URL}/`} element={<Main />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
