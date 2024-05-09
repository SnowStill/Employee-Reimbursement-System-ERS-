import { useState } from "react"
import { UserInterface } from "../../interfaces/UserInterface"
import { useNavigate } from "react-router-dom"
import axios from "axios"

export const Register: React.FC = () => {

    //set state (UserInterface)
    const[user, setUser] = useState<UserInterface>({
        firstname:"",
        lastname:"",
        username:"",
        password:""
    })

    //useNavigate to navigate between components
    const navigate = useNavigate()


    //function to store input box values
    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

        if(input.target.name === "username"){
            setUser((user) => ({...user, username:input.target.value}))
        } 
        else if(input.target.name === "firstname")
        {
            setUser((user) => ({...user, firstname:input.target.value}))
        }
        else if(input.target.name === "lastname")
        {
            setUser((user) => ({...user, lastname:input.target.value}))
        }
        else
        {
            setUser((user) => ({...user, password:input.target.value}))
        }

    }


   
    const register = async () => {
    
        const response = await axios.post("http://localhost:8080/users", user,{withCredentials : true})
        .then((response) => {
            //console.log(user)
        alert(response.data) //"{user} was created!"

        //after registration, send the user back to login page
        navigate("/employee")
        }).catch((error) => { alert(error.response.data)})
        

    }


    return(
        <div className="login">

        <div className="text-container">
            <h1>New here? Create an Account for free!</h1>

            <div className="input-container">
                <input type="text" placeholder="first name" name="firstname" onChange={storeValues}/>
            </div>
            
            <div className="input-container">
                <input type="text" placeholder="last name" name="lastname" onChange={storeValues}/>
            </div>

            <div className="input-container">
                <input type="text" placeholder="username" name="username" onChange={storeValues}/>
            </div>
            <div className="input-container">
                <input type="password" placeholder="password" name="password" onChange={storeValues}/>
            </div>


            <button className="login-button" onClick={register}>Submit</button>
            <button className="login-button" onClick={() => navigate("/employee")}>Back</button>

        </div>

        </div>
    )
}