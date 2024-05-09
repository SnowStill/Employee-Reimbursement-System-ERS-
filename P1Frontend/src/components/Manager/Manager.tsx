import { useNavigate } from "react-router-dom"
import "./Manager.css"
import { useState } from "react"
import axios from "axios"
import { state } from "../../globalData/store"
import { UserInterface } from "../../interfaces/UserInterface"



export const Manager: React.FC = () => {
    const navigate = useNavigate();

    return (
        <div className="manager">
            <div className="manager-container">
                <h1>Welcome to Manager Page!</h1>
                <h2>What would you like to do?</h2>
                <button className="users-button" onClick={() => navigate("/collection")}>Manage users</button>
                <button className="reims-button" onClick={() => navigate("/reimcollection")}>List All Reimbursement tickets</button>
                <button className="pending-button" onClick={() => navigate("/pendingreim")}>List All Pending Reimbursement tickets</button>
            </div>
        </div>
    )
}
   
