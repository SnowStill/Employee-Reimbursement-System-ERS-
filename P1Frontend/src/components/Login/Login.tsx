import { useNavigate } from "react-router-dom"
import "./Login.css"
import { useState } from "react"
import axios from "axios"
import { state } from "../../globalData/store"
import { UserInterface } from "../../interfaces/UserInterface"

export const Login: React.FC = () => {

    //defining a state object for our user data
    const[user, setUser] = useState<UserInterface>({
        username:"",
        password:""
    })

    //we need a useNavigate hook to allow us to navigate between components... no more manual URL changes!
    const navigate = useNavigate()

    //function to store input box values
    const storeValues = (input:any) => {
 
        //if the input that has changed is the "username" input, change the value of username in the user state object
 
        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } else {
            setUser((user) => ({...user, password:input.target.value}))
        }
    }

    //this function will (EVENTUALLY) gather username and password, and send a POST to our java server
    const login = async () => {

        console.log(user)
        const response = await axios.post("http://localhost:8080/users/login", user,{withCredentials:true})
        .then((response) => {

            //if the login was successful, log the user in and store their info in global state
            state.userSessionData = response.data
            
            console.log(state.userSessionData)

            alert("Welcome, " + state.userSessionData.username)

            //use our useNavigate hook to switch views to the Catch Pokemon Component
            if (state.userSessionData.role === "Employee")
            {
                navigate("/employee")
            }
            else
            {
                navigate("/manager")
            }

        })
        .catch((error) => {alert("your username or password is incorrect, Please try again!")}) //If login fails, tell the user that

    }


    return(
        <div className="login">
            <div className="text-container">
                <h1>Employee Reimbursement System</h1>
                <h2>Login</h2>

                <div className="input-container">
                    <input type="text" placeholder="username" name="username" onChange={storeValues}/>
                </div>

                <div className="input-container">
                    <input type="password" placeholder="password" name="password" onChange={storeValues} />
                </div>

                <button className="login-button" onClick={login}>Login</button>
                
            </div>

        </div>
 
    )

}