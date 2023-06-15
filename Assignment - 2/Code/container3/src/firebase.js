
// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Import firestore database from firebase for current application
import {getFirestore} from 'firebase/firestore'


// Your web app's Firebase configuration
const firebaseConfig = {
  apiKey: "AIzaSyBEdv4GQy6vj027grB6izQkjU5bZneWfLM",
  authDomain: "csci5410-a2-c6e14.firebaseapp.com",
  projectId: "csci5410-a2-c6e14",
  storageBucket: "csci5410-a2-c6e14.appspot.com",
  messagingSenderId: "270327464023",
  appId: "1:270327464023:web:2b8bba3cb2f4bd29bb6312"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);


// Initialize Firestore
const db = getFirestore(app)
export default db;
