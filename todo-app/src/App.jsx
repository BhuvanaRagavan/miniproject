import React from "react";
import "./index.css";

function App() {
  return (
    <div className="container">
      <h1>To-Do List</h1>
      <div>
        <input type="text" placeholder="Add a new task..." />
      </div>
      <div>
        <select>
          <option>HH</option>
          {/* Add options for hours */}
        </select>
        <select>
          <option>MM</option>
          {/* Add options for minutes */}
        </select>
        <select>
          <option>AM</option>
          <option>PM</option>
        </select>
      </div>
      <div>
        <input type="text" placeholder="Enter location..." />
        <button>Add</button>
      </div>
    </div>
  );
}

export default App;
