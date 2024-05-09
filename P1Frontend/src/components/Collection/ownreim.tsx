import { useEffect, useState } from "react"
import axios from "axios"
import "./Collection.css"
import { ReimInterface } from "../../interfaces/ReimInterface"
import { useNavigate } from "react-router-dom"
export const Ownreim: React.FC = () => {

    //We could have stored a base URL here for cleaner requesting
    //const baseUrl = "http://localhost:8080/pokemon" 

    //we'll store state that consists of an Array of PokemonInterface objects
    const [reims, setReim] = useState<ReimInterface[]>([]) //start with empty array
    const navigate = useNavigate()
    //I want to get all pokemon when the component renders, so we'll use useEffect
    useEffect(() => {
        getAllReims()
    }, []) //empty array so this triggers on component load and state change

    //GET request to server to get all pokemon
    const getAllReims = async () => {


        const response = await axios.get("http://localhost:8080/reimbursements/listown", {withCredentials:true})
        .then((response) => {
            setReim(response.data)
        })
        .catch((error) => { alert(error.response.data)})

        //populate the pokemon state  

    
    }
    console.log(reims)
    //Delete pokemon by id

    return(
        <div className="collection-container">
                <div>
                    <table>
                        <thead>
                            <tr>
                                <th>Id</th>
                                <th>Amount</th>
                                <th>Description</th>
                                <th>Satatus</th>
                            </tr>
                        </thead>
                        <tbody>
                        {reims.map(reim => {
                                return (
                                    <tr key={reim.reimId}>
                                        <td>{ reim.reimId }</td>
                                        <td>{ reim.amount }</td>
                                        <td>{ reim.description }</td>
                                        <td>{ reim.status }</td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
   
        </div>
    )
}