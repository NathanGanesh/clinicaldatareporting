import React from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';
class AnalyzeData extends React.Component {
	state = {
		clinicalData: [],
		firstName: '',
		lastName: '',
		age: '',
		patientId: -1
	};

	componentDidMount() {
		axios
			.get('http://localhost:8080/clinicalservices/api/patient/analyse/' + this.props.match.params.patientId)
			.then((res) => {
				console.log(res.data);
				this.setState({
					firstName: res.data.firstName,
					lastName: res.data.lastName,
					age: res.data.age,
					clinicalData: res.data.clinicalData,
					patientId: res.data.id
				});
			});
	}

	render() {
		return (
			<div>
				<h2>Patient Details:</h2>
				First Name: {this.state.firstName} &nbsp; Last Name: {this.state.lastName} &nbsp; Age: {this.state.age}
				<h2>Clinical Report:</h2>
				{this.state.clinicalData.map((eachEntry) => (
					<TableCreator item={eachEntry} patientId={this.state.patientId} key={eachEntry.patient_id} />
				))}
			</div>
		);
	}
}

class TableCreator extends React.Component {
	render() {
		const eachEntry = this.props.item;
		const patientId = this.props.patientId;
		console.log(this.props);
		return (
			<div>
				<table>
					<tr>
						<td>
							<b>{eachEntry.componentName}</b>
						</td>
					</tr>
					<tr>
						<td>{eachEntry.componentName}</td>
						<td>{eachEntry.componentValue}</td>
						<td>{eachEntry.measuredDateTime}</td>
						<td>
							<Link to={'/chart/' + eachEntry.componentName + '/' + this.props.patientId}>
								click here for the chart
							</Link>
						</td>
					</tr>
				</table>
			</div>
		);
	}
}

export default AnalyzeData;
