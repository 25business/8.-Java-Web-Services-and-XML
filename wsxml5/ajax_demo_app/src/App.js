import React from 'react';
import Promo from './Promo'
import loading from './loading.gif'
import AnimeDetails from './AnimeDetails';

export default class App extends React.Component {

  constructor(props = {}) {
    super()
    this.state = {
      isLoaded: false,
      promos: [], 
      animeDetails: null
    }

    this.getAnimeDetails = this.getAnimeDetails.bind(this)
    this.closeAnimeDetails = this.closeAnimeDetails.bind(this)
  }

  componentDidMount() {
    const xhr = new XMLHttpRequest()

    xhr.onload = () => {
      if(xhr.status == 200 && xhr.readyState == 4) {
        const data = JSON.parse(xhr.responseText)
        this.setState({
          promos: data['data'], 
          isLoaded: true,
          animeDetails: null 
        })
      }
    }

    xhr.open("GET", "https://api.jikan.moe/v4/watch/promos/popular")
    xhr.send()
  }

  getAnimeDetails(anime) {
    this.setState({ animeDetails: anime })
  }

  closeAnimeDetails() {
    this.setState({ animeDetails: null })
  }

  render() {
    let content_component = (<div>{loading}</div>)
    if(this.state.animeDetails != null) {
      content_component = <AnimeDetails 
      anime={this.state.animeDetails}
      closeAnimeDetails={this.closeAnimeDetails} />
    } else if (this.state.isLoaded) {
      const promo_components = this.state.promos.map( promo => 
      <Promo key={promo.entry.mal_id} promo={promo}
      getAnimeDetails={this.getAnimeDetails} /> )
      content_component = <div className='PromoGrid'>
        {promo_components}
      </div>
    }


    return(<div className="AjaxApp">
      {content_component}
    </div>)

  }
}