import React from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import { Link } from 'react-router-dom';
import 'react-toastify/dist/ReactToastify.css';

toast.configure();
class AddPatient extends React.Component {
	state = {
		firstName: '',
		lastName: '',
		age: ''
	};

	handleSubmit = (event) => {
		event.preventDefault();
		const data = {
			firstName: this.state.firstName,
			lastName: this.state.lastName,
			age: this.state.age
		};
		axios.post('http://localhost:8080/clinicalservices/api/patients', data).then((res) => {
			console.log(res);
			toast('Patient added successfully!', { autoClose: 2000, position: toast.POSITION.BOTTOM_CENTER });
		});
	};

	handleInputChange = (event) => {
		this.setState({
			[event.target.name]: event.target.value
		});
	};

	render() {
		return (
			<div>
				<h2>Create Patient:</h2>
				<form>
					First Name: <input type="text" name="firstName" onChange={this.handleInputChange} />
					Last Name: <input type="text" name="lastName" onChange={this.handleInputChange} />
					Age: <input type="text" name="age" onChange={this.handleInputChange} />
					<button onClick={this.handleSubmit}>Confirm</button>
				</form>
				<Link to={'/'}>Go Back</Link>
			</div>
		);
	}
}

export default AddPatient;
