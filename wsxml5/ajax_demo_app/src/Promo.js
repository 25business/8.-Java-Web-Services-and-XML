import React from 'react';
import './Promo.css';

export default class Promo extends React.Component {

    constructor(props = {}) {
        super(props)
        this.state = {
            loadTrailer: false
        }
        this.showTrailer = this.showTrailer.bind(this)
        this.showAnimeDetails = this.showAnimeDetails.bind(this)
    } 

    showTrailer() {
        this.setState({ loadTrailer: true })
    }

    showAnimeDetails(mal_id) {
        /*
        - showAnimeDetails:
          - XHR ka - https://api.jikan.moe/v4/anime/<mal_id>
          - U onload XHR-a treba da pozovem metodu iz App klase
          koja menja state za element animeDetails - prosledjujem
          parametar sa podaci o anime-u
        - App
          - U state ima animeDetails koji je null
          - u render() treba if koji proverava da li je animeDetails
          null ili ne. Ako nije renderujem komponentu
          AnimeDetails (saljem prop anime). 
          U suprotnom prikazuje se promoComponents.
        */
       const xhr = new XMLHttpRequest()

       xhr.onload = () => {
           if(xhr.status == 200 && xhr.readyState == 4) {
               let anime = JSON.parse(xhr.responseText)
               anime = anime['data']
               this.props.getAnimeDetails(anime)
           }
       }

       xhr.open("GET", "https://api.jikan.moe/v4/anime/" + mal_id)
       xhr.send()
    }

    render() {
        const trailer_element = this.state.loadTrailer ?
        (<iframe className="PromoYoutube"
            title={this.props.promo.entry.title}
        src={this.props.promo.trailer.embed_url} 
        frameborder="0" 
        allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>) : 
        (<img className='PromoPoster' onClick={this.showTrailer} src={this.props.promo.trailer.images.large_image_url} />)

        const mal_id = this.props.promo.entry.mal_id

        return (
            <div className='Promo'>
                <h1>{this.props.promo.entry.title}</h1>
                <div className='TrailerContainer'>{trailer_element}</div>
                <button className='PromoDetailsButton' onClick={(e) => { this.showAnimeDetails(mal_id)}}>Details</button>
            </div>
        )
    }
}