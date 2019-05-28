import React from 'react';
import ReactDOM from 'react-dom';
import Menu from '../../Menu/Menu';

function Footer() {
  function homeClick() {
	    ReactDOM.render(<Menu />, document.getElementById('contenedor'));
	  }
	return (
    <div className="col-sm-12 row">
     	<button type="button" className="btn btn-secondary" onClick={homeClick}>Regresar</button>
    </div>
  );
}

export default Footer;
