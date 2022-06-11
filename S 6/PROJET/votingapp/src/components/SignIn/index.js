import React, {useState} from 'react';
import {Redirect} from "react-router-dom";
import axios from 'axios'
import {
  Container,
  FormWrap,
  Icon,
  FormContent,
  Form,
  FormH1,
  FormLabel,
  FormInput,
  FormButton,
  Text
} from './SigninElements';

const SignIn = () => {


  const [EmailReg, SetEmailReg] = useState("");

  const [PasswordReg, SetPasswordReg] = useState("");

  const [LoginStatus, SetLoginStatus] = useState(false)
  const [err, SetErr] = useState(false)
  const [errMessage, SetErrMessage] = useState(false)



  function validateEmail(email)
  {
    const re = /\S+@\S+\.\S+/;
    return re.test(email);
  }


  const handelLog = () => {
    if(validateEmail(EmailReg)) {
      axios.post("http://localhost:3001/api/login", {
        email: EmailReg,
        password: PasswordReg
      }).then(response => {
        if (response.status === 200) {
          SetLoginStatus(true)
          //  instance.defaults.headers.common['authorization'] = `Bearer ${response.data.accessToken}`;
          console.log(localStorage.getItem("JWT"))
          SetLoginStatus(true)

        }
        SetErr(true)
      }).catch(err => {
        console.log(err);
        console.log("LOG ERROR ")
        SetErr(true)

      });
    }else{
      SetErr(true)
      SetErrMessage("Entrez un email correct")
    }

  }
  return (
    <>
      <Container>
        <FormWrap>
          <Icon to='/'>Ivoting Connexion </Icon>
          <FormContent>
            <Form action='#'>
              <FormH1>Sign in to your account</FormH1>
              { err ? (<FormH1> {errMessage} </FormH1>) : null}

              <FormLabel htmlFor='for'>Email</FormLabel>
              <FormInput type='email'  required onChange={(e)=>{
               SetEmailReg(e.target.value)
              }} />
              <FormLabel htmlFor='for'>Password</FormLabel>
              <FormInput type='password' required  onChange={(e)=>{
                SetPasswordReg(e.target.value)
              }}/>
              <FormButton type='submit' onClick={handelLog}>Continue</FormButton>
              { LoginStatus ? (<Redirect push to="/candidate"/>) : null }

              <Text>Forgot password</Text>
            </Form>
          </FormContent>
        </FormWrap>
      </Container>
    </>
  );
};

export default SignIn;
