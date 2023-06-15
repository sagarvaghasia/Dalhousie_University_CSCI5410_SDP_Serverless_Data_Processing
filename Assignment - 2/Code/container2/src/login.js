import {useState} from "react";
import database from './firebase'
import {collection, where, getDocs, query, updateDoc, Timestamp, addDoc, doc} from "firebase/firestore"
import {useNavigate} from "react-router-dom";
import './style.css'

const LoginForm = props => {

    const navigate = useNavigate();
    const [email, setEmail] = useState('')
    const [password, setPassword] = useState('')

    const [form, setForm] = useState({
        email: "",
        password: "",
    });

  const onUpdateField = e => {
    const nextFormState = {
      ...form,
      [e.target.name]: e.target.value,
    };
    setForm(nextFormState);
  };

  const onSubmitForm = async(e) => {
    e.preventDefault();
    // console.log('test ')
    if(form.email === ''){
      alert("email required")
      return
    }
    if(form.password === ''){
      alert("password required")
      return
    }

    const db = database;

    const loginCollectionRef = collection(db, "register")
    const userQuery = query(loginCollectionRef, where('email','==',form.email), where('password','==',form.password))

    const querySnapshot = await getDocs(userQuery);

    if(querySnapshot.empty){
        alert('Invalid username and password')
    }
    else{
        const d = querySnapshot.docs[0]

        localStorage.setItem("authenticated", true)

        const q = query(collection(db, "State"), where("email", "==", form.email))

        const docs = await getDocs(q);

        let stateId

        if(docs.docs.length > 0){
          console.log(docs.docs[0])
            await updateDoc(doc(db, "State", docs.docs[0].id), {
              "state": "online",
              "timestamp": Timestamp.now()
            })

            // data["stateId"] = docs.docs[0].id
            stateId = docs.docs[0].id
        }

        else{
          const docRef = await addDoc(collection(db, "State"), {
            "email": form.email,
            "name": d.data().name,
            "state": "online",
            "timestamp": Timestamp.now()
          })

          // data["stateId"] = docRef.id
          stateId = docRef.id
        }

        localStorage.setItem("stateId", stateId)
        navigate('/profile',{state: d.data()})
    }

  };


return (
    <form onSubmit={onSubmitForm}>
    <h3>Login</h3>
      <div>
        <label>Email : </label>
        <input
          type="email"
          aria-label="Email field"
          name="email"
          value={form.email}
          onChange={onUpdateField}
        />
      </div>
      <div>
        <label>Password : </label>
        <input
          type="password"
          aria-label="Password field"
          name="password"
          value={form.password}
          onChange={onUpdateField}
        />
      </div>
      <div>
        <button type="submit"> Login </button>
      </div>
    </form>
  );
};

export default LoginForm;