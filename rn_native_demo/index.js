import React, { Component } from 'react'
import {
    View,
    Text,
    TextInput,
    StyleSheet,
    Button,
    Platform,
    Image
} from 'react-native'
import ImageCrop from './ImageCrop';

const ASPECT_X = "2"
const ASPECT_Y = "1"

export default class Index extends Component {
    constructor(props){
        super(props)
        this.state = {
            result: ''
        }
    }

    onSelectCrop() {
        let x=this.aspectX?this.aspectX:ASPECT_X;
        let y=this.aspectY?this.aspectY:ASPECT_Y;
        ImageCrop.selectWithCrop(parseInt(x),parseInt(y)).then(result=> {
            this.setState({
                result: result['imageUrl']?result['imageUrl']:result
            })
        }).catch(e=> {
            this.setState({
                result: e
            })
        });
    }

    render() {
        let imgUrl = Platform.OS === 'android' ? 'file:///' + this.state.result : this.state.result
        let imageView = this.state.result === "" ? null : 
            <Image
                resizeMode='contain'
                style={{height: 200, width: 200}}
                source={{uri: imgUrl}}
            />
        return (
            <View style={styles.container} >
                <View style={styles.row} >
                    <Text>宽</Text>
                    <TextInput
                        style={styles.input}
                        defaultValue={ASPECT_X}
                        onChangeText={aspectX => this.aspectX = aspectX}
                    />
                    <Text>比 高:</Text>
                    <TextInput 
                        style={styles.input}
                        defaultValue={ASPECT_Y}
                        onChangeText={aspectY => this.aspectY = aspectY}
                    />
                    <Button onPress={() => this.onSelectCrop()} >裁剪图片</Button>
                </View>
                <Text>{imgUrl}</Text>
                {imageView}
            </View>
        )
    }
}

const styles = StyleSheet.create({
    container:{
        flex:1,
        marginTop:20
    },
    input:{
        height:40,
        width:40
    },
    row:{
        flexDirection:'row',
        alignItems:'center'
    }
})