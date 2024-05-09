import { useState } from "react"
import { useNavigate } from "react-router-dom"
import axios from "axios"
import { ReimInterface } from "../../interfaces/ReimInterface"
import "./Login.css"
export const Newreim: React.FC = () => {

    //set state (UserInterface)
    const[reim, setReim] = useState<ReimInterface>({
        reimId:0,
        amount:0,
        description:"",
        status:"",
        userId:0
    })

    //useNavigate to navigate between components
    const navigate = useNavigate()


    //function to store input box values
    const storeValues = (input:any) => {

        //if the input that has changed is the "username" input, change the value of username in the user state object

        if(input.target.name === "amount"){
            setReim((reim) => ({...reim, amount:input.target.value}))
        } 
        else if(input.target.name === "description")
        {
            setReim((reim) => ({...reim, description:input.target.value}))
        }
        else if(input.target.name === "status")
        {
            setReim((reim) => ({...reim, status:input.target.value}))
        }
        else
        {
            setReim((reim) => ({...reim, userId:input.target.value}))
        }
    }

    console.log(reim)
   
    const newreim = async () => {
    
        const response = await axios.post("http://localhost:8080/reimbursements/add", reim,{withCredentials : true})
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
            <h1>Create a new Reimbursement</h1>

            <div className="input-container">
                <input type="text" placeholder="amount" name="amount" onChange={storeValues}/>
            </div>
            
            <div className="input-container">
                <input type="text" placeholder="description" name="description" onChange={storeValues}/>
            </div>

            <div className="input-container">
                <input type="text" placeholder="status" name="status" onChange={storeValues}/>
            </div>

            <div className="input-container">
                <input type="text" placeholder="userId" name="userId" onChange={storeValues}/>
            </div>
            <button className="login-button" onClick={newreim}>Submit</button>
            <button className="login-button" onClick={() => navigate("/employee")}>Back</button>

        </div>

        </div>
    )
}