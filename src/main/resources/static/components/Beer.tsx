import React from 'react';

type Props = {}
type State = { beer }

class Beer extends React.Component<Props, State> {

    constructor(props) {
        super(props);
        this.state = {beer: {}}
    }

    componentDidMount() {
        this.setNextRandomBeer();
    }

    nextBeer() {
        this.setNextRandomBeer();
    }

    render() {
        const beer = this.state.beer;
        return (
            <div className="row">
                <div className="col-sm-6 app align-self-center">
                    <div className="card margin-40">
                        <div className="card-body">
                            <h5 className="card-title">{beer.name}</h5>
                            <p className="card-text">{beer.description}</p>
                            <p className="card-text"><b>Alcohol by volume:</b> {beer.abv}%</p>
                            <p className="card-text"><b>Brewery location:</b> {beer.breweryLocation}</p>
                            <br/>
                            <div onClick={this.nextBeer.bind(this)} className="btn btn-success">Show another beer</div>
                        </div>
                    </div>
                </div>
                <div className="col-sm-6">
                    <div className="beer-photo margin-40">
                        <img src={beer.imageBase64} alt="No image found"/>
                    </div>
                </div>
            </div>

        )
    }

    private setNextRandomBeer() {
        const currBeer = this.state.beer.name || null;
        fetch(`/api/beer/random?currBeer=${currBeer}`)
            .then(response => response.json())
            .then(response => {
                if (response.success) {
                    const beer = response.data;
                    this.setState({
                        beer: beer
                    })
                } else {
                    alert(response.errorMessage);
                }
            });
    }
}

export default Beer;
