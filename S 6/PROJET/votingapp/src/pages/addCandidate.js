import React, {useState} from 'react'
import './addCandidate.css'
import candidate from "./candidate";
import axios from "axios"
const AddCandidate = () => {
    const [ArrayCandidates, setArrayCandidates] = useState([]);
    const [FirstName, SetFirstName] = useState('')
    const [Name, SetName] = useState('')
    const [Project, SetProject] = useState('')
    const [ProjectDescription, SetProjectDescription] = useState('')
    const [ErrorForm, SetErrorForm] = useState(false);
    const [ErrorValidate, SetErrorValidate] = useState(false);
    let jsonCandidates;

    function checkFields(candidate){
        return  candidate.firstName &&
            candidate.lastName &&
            candidate.Project &&
            candidate.ProjectDescription
    }
    function handelNewCandidate() {

        let newCandidate = {
            firstName: FirstName,
            lastName: Name,
            Project: Project,
            ProjectDescription: ProjectDescription
        };
        if(!checkFields(newCandidate)){
            SetErrorForm(true)

        }
        else {
            setArrayCandidates((ArrayCandidates) => [...ArrayCandidates, newCandidate]


            );
            SetErrorForm(false);
        }


    }
    function validateCandidats() {
        try {
            if (ArrayCandidates.length == 0 || ArrayCandidates.length == 1) {
                SetErrorValidate(true);
            } else {
                SetErrorValidate(false)
                axios.post("http://localhost:3001/api/validateCandidates", {
                    candidates: JSON.stringify(ArrayCandidates)
                }).then((response) =>{
                    console.log(response)
                })
            }
        }catch (e){
            SetErrorValidate(true);
        }
    }

    return (

        <div className="container">
            <div className="row">
                <div className="col-xs-12 col-sm-12 col-md-12 col-lg-12 text-center">
                    <h2>ADD CANDIDATE</h2>
                </div>
            </div>
            { ErrorForm ? (<p >Remplisez tout les champs  </p>) : null}
            { ErrorValidate ? (<p >Ajoutez au moins deux candidats </p>) : null}

            <div className="row">
                <div className="col-xs-6 col-sm-6 col-md-6 col-lg-6 col-xs-offset-3">
                        <div className="form-group">
                            <label className="form-label" htmlFor="name">Nom</label>
                            <input type="text" className="form-control" id="name" name="name" placeholder="Nom"
                                   tabIndex={1} required
                                   onChange={(e) => {
                                       SetFirstName(e.target.value)
                                   }}/>
                        </div>
                        <div className="form-group">
                            <label className="form-label" htmlFor="email">Prenom</label>
                            <input type="text" className="form-control" id="email" name="email" placeholder="Prenom"
                                   tabIndex={2} required onChange={(e) => {
                                SetName(e.target.value)
                            }}/>
                        </div>
                        <div className="form-group">
                            <label className="form-label" htmlFor="subject">Projet</label>
                            <input type="text" className="form-control" id="subject" name="subject" placeholder="projet"
                                   tabIndex={3} required onChange={(e) => {
                                SetProject(e.target.value)
                            }
                            }/>
                        </div>
                        <div className="form-group">
                            <label className="form-label" htmlFor="message">Description du projet</label>
                            <textarea rows={5} cols={50} name="message" className="form-control" id="message"
                                      placeholder="description.... " tabIndex={4} required defaultValue={""}
                                      onChange={(e) => {
                                          SetProjectDescription(e.target.value)
                                      }}/>
                        </div>
                        <div className="form-group">
                            <input type="file" name="file" id="input-files" className="form-control-file border"/>
                        </div>
                        <div className="text-center">
                            <button type="submit" onClick={handelNewCandidate} className="btn btn-start-order">Add
                                candidate
                            </button>
                            <button type="submit" onClick={validateCandidats} className="btn btn-start-order">Validate Candidates</button>
                        </div>

                </div>
                <h4> Candidates </h4>
                <ol>
                    {ArrayCandidates.map((reptile) => (
                        <li>{reptile.firstName +" " + reptile.lastName}</li>
                    ))}
                </ol>
            </div>
        </div>
    )
}

export default AddCandidate;