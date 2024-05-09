import { useEffect, useState } from "react"
import axios from "axios"
import "./pending.css"
import { ReimInterface } from "../../interfaces/ReimInterface"
import { useNavigate } from "react-router-dom"
export const Pendingreim: React.FC = () => {

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


        const response = await axios.get("http://localhost:8080/reimbursements/listallpending", {withCredentials:true})
        .then((response) => {
            setReim(response.data)
            //console.log(response.data)
        })
        .catch((error) => { alert(error.response.data)})

        //populate the pokemon state  

    }
   

    const approve = async (reimId: number | undefined) => {

        //TODO: throw some error if pokeId is typeof undefined
        console.log(reimId)
        const response = await axios.patch("http://localhost:8080/reimbursements/approve/"+reimId,{withCredentials: true})
            .then((response) => alert(response.data))
            .catch(
                (error) => alert(error.response.data)
                //TODO: we could have some catches here for the errors that can pop up
            )

    }
    
    const decline = async (reimId: number | undefined) => {

        //TODO: throw some error if pokeId is typeof undefined
        const response = await axios.patch("http://localhost:8080/reimbursements/decline/"+reimId,{withCredentials: true})
            .then((response) => alert(response.data))
            .catch(
                (error) => alert(error.response.data)
                //TODO: we could have some catches here for the errors that can pop up
            )

    }
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
                                        <input type="text" placeholder="status" name="status" />
                                        <button className="resolve-button" onClick={() => approve(reim.reimId)}>Approve</button>
                                        <button className="resolve-button" onClick={() => decline(reim.reimId)}>Decline</button>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
          
        </div>
    )
}