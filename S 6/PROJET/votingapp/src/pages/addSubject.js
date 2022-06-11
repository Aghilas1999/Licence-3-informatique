import React from 'react'
import './addSubject.css'

const addSubject = () => {


  return (
    <form class="form-style-7">
    <ul>
    <li>
        <label for="name">Subject1</label>
        <input type="text" name="name" maxlength="100"/>
        <span>enter your  subject</span>
    </li>
    <li>
        <label for="name">Subject2</label>
        <input type="text" name="name" maxlength="100"/>
        <span>enter your  subject</span>
    </li>
    <li>
        <label for="name">Subject3</label>
        <input type="text" name="name" maxlength="100"/>
        <span>enter your  subject</span>
    </li>
    <li>
        <label for="name">Subject4</label>
        <input type="text" name="name" maxlength="100"/>
        <span>enter your  subject</span>
    </li>
    <li>
        <input type="submit" value="Send This" />
    </li>
    </ul>
    </form>
  )
}

export default addSubject