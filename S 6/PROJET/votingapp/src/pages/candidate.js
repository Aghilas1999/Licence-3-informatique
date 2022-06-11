import React from 'react'
import "./candidateCss.css"

const candidate = () => {
  return (
            <form onsubmit="event.preventDefault()" class="box"> 
              <h1 id='vote'>Ivoting Voters Access </h1> 
              <p id='welcom'> Welcome please enter the username and password that you have received</p> 
              <input type="text" name="" placeholder="Username"/> 
              <input type="password" name="" placeholder="Password"/> 
              <input type="submit" name="" value="Enter Poll" href="#"/> 
     
            </form> 
  )
}

export default candidate