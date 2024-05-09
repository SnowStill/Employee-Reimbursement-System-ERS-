import { useNavigate } from "react-router-dom"
import "./Employee.css"
import { useState } from "react"
import axios from "axios"
import { state } from "../../globalData/store"
import { UserInterface } from "../../interfaces/UserInterface"


export const Employee: React.FC = () => {
    const navigate = useNavigate();

    return(
        <div className="employee">
            <div className="employee-container">
                <h1>Welcome to Employee Page!</h1>
                <h2>What would you like to do?</h2>
                <button className="reg-button" onClick={() => navigate("/register")}>Create New Account</button>
                <button className="new-button" onClick={() => navigate("/newreim")}>Create New reimbursement</button>
                <button className="reims-button" onClick={() => navigate("/ownreim")}>Reimbursement tickets</button>
                <button className="pending-button" onClick={() => navigate("/pendingownreim")}>Pending Reimbursement tickets</button>
                
            </div>
        </div>


    )
}
