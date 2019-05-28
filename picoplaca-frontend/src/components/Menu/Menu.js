import React from 'react';
import ReactDOM from 'react-dom';
import './Menu.css';
import agregar from '../../assets/img/agregar.jpg';
import buscar from '../../assets/img/buscar.png';
import reglamento from '../../assets/img/reglamento.jpg';
import NuevoVehiculo from '../Vehiculo/NuevoVehiculo';
import ConsultaPicoPlaca from '../ConsultaPicoPlaca/ConsultaPicoPlaca';
import Reglamento from '../Reglamento/Reglamento';

const opciones = [
	{img:agregar, name:"Vehículo", text:"Añade un nuevo vehículo a los registros."},
	{img:reglamento, name:"Reglas", text:"Crea y revisa los reglamentos de pico y placa."},
	{img:buscar, name:"Consulta", text:"Revisa si tu vehículo puede circular."}
];

function Opcion(props){
	function handleToggleClick(accion) {
		switch(accion) {
	    case 'Vehículo':
				ReactDOM.render(<NuevoVehiculo />, document.getElementById('contenedor'));
				break;
	    case 'Reglas':
				ReactDOM.render(<Reglamento />, document.getElementById('contenedor'));
				break;
	    case 'Consulta':
				ReactDOM.render(<ConsultaPicoPlaca />, document.getElementById('contenedor'));
				break;
	    default:
	      return false;
	  }
	}
	return (
		<div className="col-md-4 p-5" onClick={handleToggleClick.bind(this, props.name)}>
			<div className="card bg-dark text-white  mb-3">
			  <img className="card-img-top" src={props.img}/>
			  <div className="card-body">
			    <h5 className="card-title">{props.name}</h5>
			    <p className="card-text">{props.text}</p>
			    <a href="#" className="btn btn-primary" onClick={handleToggleClick.bind(this, props.name)}>Vamos!</a>
			  </div>
			</div>
		</div>
		);
}

function Menu() {
	const opcionesRendered = opciones.map((itemOpcion)=>
		<Opcion key={itemOpcion.name} img={itemOpcion.img} name={itemOpcion.name} text={itemOpcion.text}/>
		);
  return (
    <div className="col-sm-12 row">
     	{opcionesRendered}
    </div>
  );
}

export default Menu;
