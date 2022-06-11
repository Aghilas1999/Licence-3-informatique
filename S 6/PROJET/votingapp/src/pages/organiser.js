
import React , {useState} from 'react'
import "./org.css"
import axios from 'axios'


const Organiser = () =>{
    const [FullName, SetFullName] = useState('')
    const [TextArea, SetTextArea] = useState('');
    const [Address, SetAddress] = useState('');
    const [email, SetEmail] = useState('');
    const [password, Setpassword] = useState('');



    const handelSubmit = ()=>{

        axios.post("http://localhost:3001/api/addOrganisator" ,{
            fullName : FullName,
            address : Address,
            email : email,
            password: password
        }).then((r)=>{
            console.log("Done ")
            console.log(r)
        }).catch((err)=>{
            console.log("Error")
            console.log(err)
        })
    }





    return (
        <div className="form-style-10">
            <h1>
                Ivoting registration
                <span>Sign up and tell us what you think of the site!</span>
            </h1>
            <form>
                <div className="section">
                    <span>1</span>Full Name &amp; Address
                </div>
                <div className="inner-wrap">
                    <label>
                        Your Full Name <input type="text" name="field1" required="" onChange={(e) => {
                        SetFullName(e.target.value)
                    }}/>
                    </label>
                    <label>
                        Address <textarea name="field2" required="" defaultValue={""} onChange={(e) => {
                        SetAddress(e.target.value)
                    }}/>
                    </label>
                </div>
                <div className="section">
                    <span>2</span>Email &amp; Phone
                </div>
                <div className="inner-wrap">
                    <label>
                        Email Address <input type="email" name="field3" required="" onChange={(e)=>{
                            SetEmail(e.target.value)
                    }} />
                    </label>
                    <label>
                        Phone Number <input type="text" name="field4" />
                    </label>
                </div>
                <div className="section">
                    <span>3</span>Passwords
                </div>
                <div className="inner-wrap">
                    <label>
                        Password <input type="password" name="field5"  onChange={(e)=>{
                        Setpassword(e.target.value)
                    }} />
                    </label>
                    <label>
                        Confirm Password <input type="password" name="field6"/>
                    </label>
                </div>
                <div className="button-section">
                    <button type="button" name="Sign Up" onClick={handelSubmit} > Sign in </button>
                </div>
            </form>
        </div>
    )}
export default Organiser