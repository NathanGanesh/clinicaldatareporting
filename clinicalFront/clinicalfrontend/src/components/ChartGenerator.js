import React from 'react';
import axios from 'axios';
import { Line } from 'react-chartjs-2';
const initData = {
	labels: [],
	dataSets: [
		{
			label: 'Heartrate',
			fill: false,
			borderWidth: 8
		}
	]
};
class ChartGenerator extends React.Component {
	constructor(props) {
		super(props);
		this.state = {
			chartData: initData
		};
	}

	componentDidMount() {
		axios
			.get('http://localhost:8080/clinicalservices/api/patient/analyse/' + this.props.match.params.patientId)
			.then((res) => {
				console.log(res.data);
				var heartRateData = res.data.clinicalData.filter(
					(component) => component.componentName === 'heartrate'
				);
				console.log(heartRateData);

				for (let i = 0; i < heartRateData.length; i++) {
					initData.labels[i] = heartRateData[i].measuredDateTime;
					initData.dataSets[0].data[i] = heartRateData[i].componentValue;
				}
				// this.setState({
				// 	initData
				// });
			});
	}

	render() {
		return (
			<div>
				<Line data={this.state.chartData} />
			</div>
		);
	}
}

export default ChartGenerator;
