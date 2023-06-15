import { getDocs, updateDoc, doc } from "firebase/firestore";
import { useLocation, useNavigate } from "react-router-dom";
import db from "./firebase"


function Profile(props){

    //Reference: https://stackabuse.com/programmatically-navigate-using-react-router/
    //Reference: https://reactrouter.com/en/main/hooks/use-navigate
    const location = useLocation()
    const navigate = useNavigate()

    const logout = async(e) => {

        e.preventDefault()
        console.log(localStorage.getItem("stateId"))

        const docRef = await updateDoc(doc(db, "State", localStorage.getItem("stateId")), {
            "state": "offline"
        })

        navigate("/")
    }

    return(
        <div>
            <h1>
            <div>
                {location.state.email}
            </div>
            <div>
                {location.state.location}
            </div>
            <div>
                {location.state.name}
            </div>
        </h1>

        <form>
            <button type="submit" class="btn" onClick={logout}> Logout </button>
        </form>
        </div>

        
  
    );
}

export default Profile;