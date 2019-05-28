import React from 'react';
import logo from './logo.svg';
import './App.css';

import Header from './components/commons/Header/Header';
import Menu from './components/Menu/Menu';
import Footer from './components/commons/Footer/Footer';


function App() {
  return (
    <div className="App">
      <Header/>
      <div id="contenedor">
      <Menu />
      </div>
      <Footer />
    </div>
  );
}

export default App;
