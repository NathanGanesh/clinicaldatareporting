import React from 'react';
import axios from 'axios';
import { toast } from 'react-toastify';
import { Link } from 'react-router-dom';

class CollectClinicals extends React.Component {
	state = {
		clinicalEntryType: '',
		componentValue: '',
		patientData: []
	};

	componentWillMount() {
		axios
			.get('http://localhost:8080/clinicalservices/api/patients/' + this.props.match.params.patientId)
			.then((res) => {
				const patientData = res.data;
				this.setState({ patientData });
			});
	}

	handleSubmit = (event) => {
		event.preventDefault();
		const data = {
			patientId: this.props.match.params.patientId,
			componentName: this.state.clinicalEntryType,
			componentValue: this.state.componentValue
		};
		axios.post('http://localhost:8080/clinicalservices/api/clinicals/', data).then((res) => {
			toast('Patient Data Saved Successfully', { autoClose: 3000, position: toast.POSITION.BOTTOM_CENTER });
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
				<h2>Patient Details:</h2>
				First Name: {this.state.patientData.firstName} &nbsp; Last Name: {this.state.patientData.lastName}{' '}
				&nbsp; Age: {this.state.patientData.age}
				<h2>Patient Clinical Data:</h2>
				<form>
					Clinical Entry Type{' '}
					<select name="clinicalEntryType" onChange={this.handleInputChange}>
						<option>Select One</option>
						<option value="bp">Blood Pressure(Sys/Dys)</option>
						<option value="hw">Height/Weight</option>
						<option value="heartRate">Heart Rate</option>
					</select>
					&nbsp; Value: <input type="text" name="componentValue" onChange={this.handleInputChange} />
					<button onClick={this.handleSubmit}>Confirm</button>
				</form>
				<Link to={'/'}>Go Back</Link>
			</div>
		);
	}
}

export default CollectClinicals;
