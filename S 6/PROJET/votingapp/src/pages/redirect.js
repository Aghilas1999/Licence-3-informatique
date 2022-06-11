import React, { useState } from "react";
import { Redirect } from "react-router-dom";
import { Button } from "react-scroll";
import "./redirect.css";

export const Redirection = () => {

const [redirectVote , SetredirectVote] = useState(false)

  const [redirectSondage , SetredirectSondage] = useState(false);

  const Voteclickhandel =()=>{
    SetredirectVote(true);
  }
  const Sondageclickhandel =()=>{
    SetredirectSondage(true);
  }

  return (
    <form class="box">
      <h1 id="vote"> </h1>
      <Button type="button" name=""  onClick={Voteclickhandel} 
      value="Vote"  />
              { redirectVote ? (<Redirect push to="/addCandidate"/>) : null }
      <Button type="button" name="" value="Sondage" onClick={Sondageclickhandel} />
      { redirectSondage ? (<Redirect push to="/addSubject"/>) : null }

    </form>
  );
};
export default Redirection;
