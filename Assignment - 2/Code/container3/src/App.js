import logo from './logo.svg';
import './App.css';

import { useState, useEffect } from "react";
import { query, collection, getDocs, where } from "firebase/firestore"
import db from "./firebase"

//Reference: https://reactjs.org/docs/hooks-effect.html
//Referencce: https://www.geeksforgeeks.org/reactjs-setstate/
//Reference: https://www.pluralsight.com/guides/consume-data-from-firebase-firestore-in-a-react-app
function App() {

  const [states, setStates] = useState([])

    const getData = async () => {
    const q = query(collection(db, "State"), where("state", "==", "online"))

    const docs = await getDocs(q);

    let data = []

    docs.docs.forEach((d) => {
      data.push(d.data())
    })

    console.log(data)

    return data
  }


  useEffect(() => {

    getData().then((response) => {
      setStates(response)
    })
  }, [])

  console.log(states)


  const tdData = () => {


    return states.map((data) => {
      return (
        <tr>
          <th>{data["name"]}</th>
          <th>{data["email"]}</th>
          <th>{data["state"]}</th>
          {/* https://stackoverflow.com/questions/48689876/how-to-convert-timestamp-in-react-js */}
          {/* <th>{new Intl.DateTimeFormat('en-US', {year: 'numeric', month: '2-digit',day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit'}).format(data["timestamp"])}</th> */}
          <th>{new Date(data["timestamp"].seconds * 1000).toLocaleString()}</th>
        </tr>
      )
    })
  }


  return (
    <div className="App">
      <table className="table" border="2px">
        <thead>
          <tr>
            <th>name</th>
            <th>Email</th>
            <th>State</th>
            <th>Timestamp</th>
          </tr>
        </thead>
        <tbody>
          {tdData()}
        </tbody>
      </table>

    </div>
  );
}

export default App;
