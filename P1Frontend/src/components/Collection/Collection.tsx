import { useEffect, useState } from "react"
import axios from "axios"

import "./Collection.css"
import { UserInterface } from "../../interfaces/UserInterface"
import { useNavigate } from "react-router-dom"
import { ReimInterface } from "../../interfaces/ReimInterface"

export const Collection: React.FC = () => {

    //We could have stored a base URL here for cleaner requesting
    //const baseUrl = "http://localhost:8080/pokemon" 

    //we'll store state that consists of an Array of PokemonInterface objects
    const [users, setUser] = useState<UserInterface[]>([]) //start with empty array
    const navigate = useNavigate()
    //I want to get all pokemon when the component renders, so we'll use useEffect
    useEffect(() => {
        getAllUsers()
    }, []) //empty array so this triggers on component load and state change

    //GET request to server to get all pokemon
    const getAllUsers = async () => {


        const response = await axios.get("http://localhost:8080/users/all", {withCredentials:true})
        .then((response) => {
            setUser(response.data)
            //console.log(response.data)
        })
        .catch((error) => { alert(error.response.data)})

        //populate the pokemon state  
        
        
    }
    //Delete pokemon by id
    const deleteUser = async(userId:number| undefined) => {

        //TODO: throw some error if pokeId is typeof undefined
        console.log(userId)
        const response = await axios.delete("http://localhost:8080/users/delete/" + userId, {withCredentials:true})
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
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Username</th>
                                <th>Role</th>
                            </tr>
                        </thead>
                        <tbody>
                            {users.map(user => {
                                return (
                                    <tr key={user.userId}>
                                        <td>{ user.userId }</td>
                                        <td>{ user.firstname }</td>
                                        <td>{ user.lastname }</td>
                                        <td>{ user.username }</td>
                                        <td>{ user.role }</td>
                                        <button className="poke-button" onClick={() => deleteUser(user.userId)}>Delete</button>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>

        </div>
    )
}
