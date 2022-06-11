import React from 'react';
import './App.css';

import { BrowserRouter as Router, Switch, Route } from 'react-router-dom';
import Home from './pages';
import SigninPage from './pages/signin';
import signupPage from './pages/signup';
import Apply from './Apply';
import organiser from './pages/organiser';
import candidate from './pages/candidate';
import redirect from './pages/redirect';
import { vote } from './pages/vote';
import addCandidate from './pages/addCandidate'
import addVoter from './pages/addVoter';
import addSubject from './pages/addSubject';
import Redirection from './pages/redirect';

function App() {
  return (
    <Router>
      <Switch>
        <Route path='/' component={Home} exact />
        <Route path='/signin' component={SigninPage} exact />
        <Route path='/signup' component={signupPage} />
        <Route path='/organisation' component={organiser} />
        <Route path='/candidate' component={candidate} />
        <Route path='/redirect' component={Redirection} />
        <Route path='/vote' component={vote} />
        <Route path='/addCandidate' component={addCandidate} />
        <Route path='/addVoter' component={addVoter} />
        <Route path='/addSubject' component={addSubject} />











      </Switch>
    </Router>
  );
}

export default App;
