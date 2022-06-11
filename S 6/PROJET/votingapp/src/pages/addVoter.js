import React from 'react'
import './addVoter.css'

const addVoter = () => {
  return (
    <div class="form-style-6">
<h1>ADD VOTER</h1>
<form>
<input type="text" name="field1" placeholder="Voter Name" />
<input type="email" name="field2" placeholder="Email Address" />
<textarea name="field3" placeholder="Message and Access code that will be sent to the voter "></textarea>
<input type="submit" value="Send" />
</form>
</div>
  )
}

export default addVoter